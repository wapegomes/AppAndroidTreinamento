package br.com.wpgomes.treinamento.appcasttreinamento.util;

/**
 * Created by wgomes on 04/07/16.
 */

public interface Constants {

    String CHARACTER_TABLE = "character";

    String EVENT_TABLE = "event";

    String DDL_CHARACTER = "CREATE TABLE [character] (\n" +
            "  [id] INTEGER IDENTITY (1, 1), \n" +
            "  [name] VARCHAR(100), \n" +
            "  [description] TEXT, \n" +
            "  [link] TEXT, \n" +
            "  [image] TEXT, \n" +
            "  [favorite] INTEGER DEFAULT 0, \n"+
            "  CONSTRAINT [] PRIMARY KEY ([id]));\n";

    String DDL_EVENT = "CREATE TABLE [event] (\n" +
            "  [id] INTEGER IDENTITY (1, 1), \n" +
            "  [name] VARCHAR(100), \n" +
            "  [description] TEXT, \n" +
            "  [image] TEXT, \n" +
            "  [link] TEXT, \n" +
            "  CONSTRAINT [] PRIMARY KEY ([id]));";

    String SERVICETAG = "SEV";

    String BASEURL = "http://gateway.marvel.com";

    String API_CHARACTER = BASEURL + "/v1/public/characters";

    String API_EVENT = BASEURL + "/v1/public/events";

    String API_COMIT = BASEURL + "/v1/public/characters/{characterId}";
}
