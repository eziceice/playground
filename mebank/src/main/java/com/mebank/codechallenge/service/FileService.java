package com.mebank.codechallenge.service;

import com.mebank.codechallenge.pojo.Transaction;

import java.util.List;
import java.util.Map;

/**
 * Interface for file service
 */
public interface FileService {
    Map<String, List<Transaction>> load();
}
