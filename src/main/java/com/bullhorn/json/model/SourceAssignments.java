package com.bullhorn.json.model;

import com.google.gson.JsonObject;

import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;

public class SourceAssignments {
	@ApiModelProperty(notes="Client integration key [required]")
	private String integrationKey;
	
	@ApiModelProperty(notes="Map name [required]")
	private String mapName;
	
	@ApiModelProperty(notes="Client integration key [required]")
	private String messageId;

	@ApiModelProperty(notes="Client integration key [required]")
	private String client;
	
	@ApiModelProperty(notes="JSON String from Azure")
	private JsonObject[] data;

	public String getIntegrationKey() {
		return integrationKey;
	}

	public void setIntegrationKey(String integrationKey) {
		this.integrationKey = integrationKey;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public JsonObject[] getData() {
		return data;
	}

	public void setData(JsonObject[] data) {
		this.data = data;
	}

	public SourceAssignments(String integrationKey, String mapName, String messageId, String client, JsonObject[] data) {
		super();
		this.integrationKey = integrationKey;
		this.mapName = mapName;
		this.messageId = messageId;
		this.client = client;
		this.data = data;
	}

	public SourceAssignments() {
		super();
	}

	@Override
	public String toString() {
		return "SourceAssignments [integrationKey=" + integrationKey + ", mapName=" + mapName + ", messageId="
				+ messageId + ", client=" + client + ", data=" + Arrays.toString(data) + "]";
	}

}
