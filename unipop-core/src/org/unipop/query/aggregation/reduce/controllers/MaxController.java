package org.unipop.query.aggregation.reduce.controllers;

import org.unipop.query.aggregation.reduce.ReduceQuery;
import org.unipop.query.controller.UniQueryController;

/**
 * @author Gur Ronen
 * @since 6/29/2016
 */
public interface MaxController extends UniQueryController {
    long max(ReduceQuery reduceQuery);
}