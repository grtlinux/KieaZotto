package org.tain.tools.node;

import java.util.Map;

import org.tain.utils.CurrentInfo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MonJsonNode implements Cloneable {

	private JsonNode jsonNode;
	private MonNodeType monNodeType;

	private ObjectNode objectNode = null;  // {}
	private ArrayNode arrayNode = null;    // []

	private static final ObjectMapper objectMapper = new ObjectMapper();

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public static String getPrettyJson(Object object) {
		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}
	
	public static String getJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	public MonJsonNode(JsonNode jsonNode) {
		this.jsonNode = jsonNode;

		if (this.jsonNode.isObject()) {
			this.objectNode = (ObjectNode) jsonNode;
			this.monNodeType = MonNodeType.OBJECT_NODE;
		} else if (this.jsonNode.isArray()) {
			this.arrayNode = (ArrayNode) jsonNode;
			this.monNodeType = MonNodeType.ARRAY_NODE;
		} else {
			this.monNodeType = MonNodeType.OBJECT_VALUE;
		}
	}

	public MonJsonNode(String strJsonNode) throws Exception {
		this(new ObjectMapper().readTree(strJsonNode));
	}

	public String getType() {
		return this.monNodeType.getValue();
	}

	public JsonNode getJsonNode() {
		return this.jsonNode;
	}
	
	public String toString() {
		return this.jsonNode.toString();
	}

	public String toPrettyString() {
		return this.jsonNode.toPrettyString();
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	public void put(String fieldName, Object obj) throws Exception {
		// ObjectNode objectNode = (ObjectNode) this.jsonNode.at("/");
		if (obj instanceof String) {
			this.objectNode.put(fieldName, (String)obj);
		} else if (obj instanceof Long) {
			this.objectNode.put(fieldName, (Long) obj);
		} else if (obj instanceof Integer) {
			this.objectNode.put(fieldName, (Integer) obj);
		} else if (obj instanceof Double) {
			this.objectNode.put(fieldName, (Double) obj);
		} else if (obj instanceof Float) {
			this.objectNode.put(fieldName, (Float) obj);
		} else if (obj instanceof Boolean) {
			this.objectNode.put(fieldName, (Boolean) obj);
		} else if (obj instanceof MonJsonNode) {
			this.objectNode.set(fieldName, (JsonNode) ((MonJsonNode)obj).getJsonNode());
		} else if (obj instanceof JsonNode) {
			this.objectNode.set(fieldName, (JsonNode) obj);
		} else if (obj instanceof ObjectNode) {
			this.objectNode.set(fieldName, (ObjectNode) obj);
		} else if (obj instanceof ArrayNode) {
			this.objectNode.set(fieldName, (ArrayNode) obj);
		} else {
			throw new Exception("KANG-ERR: Wrong type..." + CurrentInfo.get());
		}
	}

	public void put(String branch, String fieldName, Object obj) throws Exception {
		ObjectNode objectNode = (ObjectNode) this.jsonNode.at(branch);
		if (obj instanceof String) {
			objectNode.put(fieldName, (String)obj);
		} else if (obj instanceof Long) {
			objectNode.put(fieldName, (Long) obj);
		} else if (obj instanceof Integer) {
			objectNode.put(fieldName, (Integer) obj);
		} else if (obj instanceof Double) {
			objectNode.put(fieldName, (Double) obj);
		} else if (obj instanceof Float) {
			objectNode.put(fieldName, (Float) obj);
		} else if (obj instanceof Boolean) {
			objectNode.put(fieldName, (Boolean) obj);
		} else if (obj instanceof MonJsonNode) {
			objectNode.set(fieldName, (JsonNode) ((MonJsonNode)obj).getJsonNode());
		} else if (obj instanceof JsonNode) {
			objectNode.set(fieldName, (JsonNode) obj);
		} else if (obj instanceof ObjectNode) {
			objectNode.set(fieldName, (ObjectNode) obj);
		} else if (obj instanceof ArrayNode) {
			objectNode.set(fieldName, (ArrayNode) obj);
		} else {
			throw new Exception("KANG-ERR: Wrong type..." + CurrentInfo.get());
		}
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	public void add(Object obj) throws Exception {
		if (obj instanceof String) {
			this.arrayNode.add((String)obj);
		} else if (obj instanceof Long) {
			this.arrayNode.add((Long) obj);
		} else if (obj instanceof Integer) {
			this.arrayNode.add((Integer) obj);
		} else if (obj instanceof Double) {
			this.arrayNode.add((Double) obj);
		} else if (obj instanceof Float) {
			this.arrayNode.add((Float) obj);
		} else if (obj instanceof Boolean) {
			this.arrayNode.add((Boolean) obj);
		} else if (obj instanceof MonJsonNode) {
			this.arrayNode.add((JsonNode) ((MonJsonNode)obj).getJsonNode());
		} else if (obj instanceof JsonNode) {
			this.arrayNode.add((JsonNode) obj);
		} else if (obj instanceof ObjectNode) {
			this.arrayNode.add((ObjectNode) obj);
		} else if (obj instanceof ArrayNode) {
			this.arrayNode.add((ArrayNode) obj);
		} else {
			throw new Exception("KANG-ERR: Wrong type..." + CurrentInfo.get());
		}
	}

	public void add(String branch, Object obj) throws Exception {
		ArrayNode arrayNode = (ArrayNode) this.objectNode.at(branch);
		if (obj instanceof String) {
			arrayNode.add((String)obj);
		} else if (obj instanceof Long) {
			arrayNode.add((Long) obj);
		} else if (obj instanceof Integer) {
			arrayNode.add((Integer) obj);
		} else if (obj instanceof Double) {
			arrayNode.add((Double) obj);
		} else if (obj instanceof Float) {
			arrayNode.add((Float) obj);
		} else if (obj instanceof Boolean) {
			arrayNode.add((Boolean) obj);
		} else if (obj instanceof MonJsonNode) {
			arrayNode.add((JsonNode) ((MonJsonNode)obj).getJsonNode());
		} else if (obj instanceof JsonNode) {
			arrayNode.add((JsonNode) obj);
		} else if (obj instanceof ObjectNode) {
			arrayNode.add((ObjectNode) obj);
		} else if (obj instanceof ArrayNode) {
			arrayNode.add((ArrayNode) obj);
		} else {
			throw new Exception("KANG-ERR: Wrong type..." + CurrentInfo.get());
		}
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	public void remove(String fieldName) throws Exception {
		this.objectNode.remove(fieldName);
	}
	
	public void remove(String branch, String fieldName) throws Exception {
		ObjectNode objectNode = (ObjectNode) this.jsonNode.at(branch);
		objectNode.remove(fieldName);
	}
	
	public void remove(int index) throws Exception {
		this.arrayNode.remove(index);
	}
	
	public void remove(String branch, int index) throws Exception {
		ArrayNode arrayNode = (ArrayNode) this.jsonNode.at(branch);
		arrayNode.remove(index);
	}
	
	///////////////////////////////////////////////////////////////////////////
	// JsonNode obj = (JsonNode) this.jsonNode.at(branch);
	public void removeProperty(Object obj, Object key) {
		if (isObjectNode(obj))
			toJsonObject(obj).remove(key.toString());
		else {
			ArrayNode array = toJsonArray(obj);
			int index = key instanceof Integer ? (Integer) key : Integer.parseInt(key.toString());
			array.remove(index);
		}
	}
	
	private boolean isObjectNode(Object obj) {
		if (obj instanceof ObjectNode) {
			return true;
		}
		return false;
	}
	
	private ObjectNode toJsonObject(Object obj) {
		return (ObjectNode) obj;
	}
	
	private ArrayNode toJsonArray(Object obj) {
		return (ArrayNode) obj;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	public ObjectNode blankObjectNode() throws Exception {
		return (ObjectNode) objectMapper.readTree("{}");
	}
	
	public ArrayNode blankArrayNode() throws Exception {
		return (ArrayNode) objectMapper.readTree("[]");
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	public void createObjectNode(String fieldName) throws Exception {
		this.put(fieldName, blankObjectNode());
	}
	
	public void createObjectNode(String branch, String fieldName) throws Exception {
		this.put(branch, fieldName, blankObjectNode());
	}
	
	public void createArrayNode(String fieldName) throws Exception {
		this.put(fieldName, blankArrayNode());
	}
	
	public void createArrayNode(String branch, String fieldName) throws Exception {
		this.put(branch, fieldName, blankArrayNode());
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	public String getText(String fieldName) {
		return this.jsonNode.get(fieldName).textValue();
	}

	public Number getNumber(String fieldName) {
		return this.jsonNode.get(fieldName).numberValue();
	}

	public Boolean getBoolean(String fieldName) {
		return this.jsonNode.get(fieldName).booleanValue();
	}

	public JsonNode getJsonNode(String fieldName) {
		return (JsonNode) this.jsonNode.get(fieldName);
	}

	public ObjectNode getObjectNode(String fieldName) {
		return (ObjectNode) this.jsonNode.get(fieldName);
	}

	public ArrayNode getArrayNode(String fieldName) {
		return (ArrayNode) this.jsonNode.get(fieldName);
	}

	public MonJsonNode getMonJsonNode(String fieldName) {
		return new MonJsonNode((JsonNode) this.jsonNode.get(fieldName));
	}

	public Map<String,?> getMap(String fieldName) throws Exception {
		return objectMapper.readValue(this.jsonNode.get(fieldName).toString()
				, new TypeReference<Map<String,?>>(){});
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	public String getText(String branch, String fieldName) {
		return this.jsonNode.at(branch).get(fieldName).textValue();
	}

	public Number getNumber(String branch, String fieldName) {
		return this.jsonNode.at(branch).get(fieldName).numberValue();
	}

	public Boolean getBoolean(String branch, String fieldName) {
		return this.jsonNode.at(branch).get(fieldName).booleanValue();
	}

	public JsonNode getJsonNode(String branch, String fieldName) {
		return (JsonNode) this.jsonNode.at(branch).get(fieldName);
	}

	public ObjectNode getObjectNode(String branch, String fieldName) {
		return (ObjectNode) this.jsonNode.at(branch).get(fieldName);
	}

	public ArrayNode getArrayNode(String branch, String fieldName) {
		return (ArrayNode) this.jsonNode.at(branch).get(fieldName);
	}

	public MonJsonNode getMonJsonNode(String branch, String fieldName) {
		return new MonJsonNode((JsonNode) this.jsonNode.at(branch).get(fieldName));
	}

	public Map<String,?> getMap(String branch, String fieldName) throws Exception {
		return objectMapper.readValue(this.jsonNode.at(branch).get(fieldName).toString()
				, new TypeReference<Map<String,?>>(){});
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Override
	public MonJsonNode clone() throws CloneNotSupportedException {
		return (MonJsonNode) super.clone();
	}
}
