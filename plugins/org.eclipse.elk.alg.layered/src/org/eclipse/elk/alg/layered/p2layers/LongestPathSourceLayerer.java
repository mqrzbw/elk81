/*******************************************************************************
 * Copyright (c) 2022 Kiel University and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0 
 *******************************************************************************/
package org.eclipse.elk.alg.layered.p2layers;

import java.util.List;

import org.eclipse.elk.alg.layered.LayeredPhases;
import org.eclipse.elk.alg.layered.graph.LEdge;
import org.eclipse.elk.alg.layered.graph.LGraph;
import org.eclipse.elk.alg.layered.graph.LNode;
import org.eclipse.elk.alg.layered.graph.LPort;
import org.eclipse.elk.alg.layered.graph.Layer;
import org.eclipse.elk.alg.layered.intermediate.IntermediateProcessorStrategy;
import org.eclipse.elk.core.alg.ILayoutPhase;
import org.eclipse.elk.core.alg.LayoutProcessorConfiguration;
import org.eclipse.elk.core.util.IElkProgressMonitor;

/**
 * Works the same as {@code LongestPathLayerer} but builds a trivial layering beginning with the sources and not the
 * sinks.
 * 
 * <dl>
 * <dt>Precondition:</dt>
 * <dd>the graph has no cycles</dd>
 * <dt>Postcondition:</dt>
 * <dd>all nodes have been assigned a layer such that edges connect only nodes from layers with
 * increasing indices</dd>
 * </dl>
 */
public class LongestPathSourceLayerer implements ILayoutPhase<LayeredPhases, LGraph> {

    /** intermediate processing configuration. */
    private static final LayoutProcessorConfiguration<LayeredPhases, LGraph> BASELINE_PROCESSING_CONFIGURATION =
            LayoutProcessorConfiguration.<LayeredPhases, LGraph>create()
            .addBefore(LayeredPhases.P1_CYCLE_BREAKING,
                    IntermediateProcessorStrategy.EDGE_AND_LAYER_CONSTRAINT_EDGE_REVERSER)
            .addBefore(LayeredPhases.P2_LAYERING, IntermediateProcessorStrategy.LAYER_CONSTRAINT_PREPROCESSOR)
            .addBefore(LayeredPhases.P3_NODE_ORDERING, IntermediateProcessorStrategy.LAYER_CONSTRAINT_POSTPROCESSOR);

    
    /** the layered graph to which layers are added. */
    private LGraph layeredGraph;
    /** map of nodes to their height in the layering. */
    private int[] nodeHeights;
    
    @Override
    public LayoutProcessorConfiguration<LayeredPhases, LGraph> getLayoutProcessorConfiguration(final LGraph graph) {
        return BASELINE_PROCESSING_CONFIGURATION;
    }
    
    @Override
    public void process(final LGraph thelayeredGraph, final IElkProgressMonitor monitor) {
        monitor.begin("Longest path to source layering", 1);
        
        layeredGraph = thelayeredGraph;
        List<LNode> nodes = layeredGraph.getLayerlessNodes();
        
        // initialize values required for the computation
        nodeHeights = new int[nodes.size()];
        int index = 0;
        for (LNode node : nodes) {
            // the node id is used as index for the nodeHeights array
            node.id = index;
            nodeHeights[index] = -1;
            index++;
        }
        
        // process all nodes
        for (LNode node : nodes) {
            visit(node);
        }
        
        // empty the list of unlayered nodes
        nodes.clear();
        
        // release the created resources
        this.layeredGraph = null;
        this.nodeHeights = null;
        
        monitor.done();
    }
    

    /**
     * Visit a node: if not already visited, find the longest path to a source.
     * 
     * @param node node to visit
     * @return height of the given node in the layered graph
     */
    private int visit(final LNode node) {
        int height = nodeHeights[node.id];
        if (height >= 0) {
            // the node was already visited (the case height == 0 should never occur)
            return height;
        } else {
            int maxHeight = 1;
            for (LPort port : node.getPorts()) {
                for (LEdge edge : port.getIncomingEdges()) {
                    LNode sourceNode = edge.getSource().getNode();
                    
                    // ignore self-loops
                    if (node != sourceNode) {
                        int sourceHeight = visit(sourceNode);
                        maxHeight = Math.max(maxHeight, sourceHeight + 1);
                    }
                }
            }
            putNode(node, maxHeight);
            return maxHeight;
        }
    }
    
    /**
     * Puts the given node into the layered graph, adding new layers as necessary.
     * 
     * @param node a node
     * @param height height of the layer where the node shall be added
     *          (height = number of layers - layer index)
     */
    private void putNode(final LNode node, final int height) {
        List<Layer> layers = layeredGraph.getLayers();
        
        // add layers so as to guarantee that number of layers >= height
        for (int i = layers.size(); i < height; i++) {
            layers.add(layers.size(), new Layer(layeredGraph));
        }
        
        // layer index = number of layers - height
        node.setLayer(layers.get(height - 1));
        nodeHeights[node.id] = height;
    }
}
