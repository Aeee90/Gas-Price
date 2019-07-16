package aeee.api.gasprice.web.vo.entity;

import com.fasterxml.jackson.databind.JsonNode;

interface InitializerWithJsonNode<T> {
    T initializWithJwonNode(JsonNode jsonNode);
}
