package aeee.api.gasprice.web.vo.entity;

import com.fasterxml.jackson.databind.JsonNode;

interface InitializeWithJsonNode<T> {
    T initializeWithJsonNode(JsonNode jsonNode);
}
