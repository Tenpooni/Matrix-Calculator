package persistence;

import model.Log;
import model.Column;
import model.Row;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads matrix from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Column readMatrix() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseColumn(jsonObject);
    }

    // EFFECTS: reads history log from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Log readLog() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLog(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //MODIFIES: this
    //EFFECTS: creates log object containing strings listing each action
    private Log parseLog(JSONObject jsonObject) {
        JSONArray logArray = jsonObject.getJSONArray("History");

        int listSize = logArray.length();
        Log log = new Log();

        for (int i = 0; i < listSize; i++) {
            String tempString = logArray.getString(i);
            log.setLogLine(i, tempString);
        }
        return log;
    }

    //MODIFIES: this
    //EFFECTS: creates new column object based on saved data
    private Column parseColumn(JSONObject jsonObject) {

        JSONArray columnArray = jsonObject.getJSONArray("Matrix");

        int columnSize = columnArray.length();
        Column col = new Column(columnSize);

        for (int i = 0; i < columnSize; i++) {
            JSONArray buildRow = columnArray.getJSONArray(i);
            Row rowToAdd = parseRows(col, buildRow);
            col.setColumn(i, rowToAdd);
        }
        return col;
    }

    //MODIFIES: this
    //EFFECTS: creates row object containing float values based on saved data
    private Row parseRows(Column col, JSONArray buildRow) {
        int rowSize = buildRow.length();
        Row row = new Row(rowSize);

        for (int i = 0; i < rowSize; i++) {
            int tempVal = buildRow.getInt(i);
            row.setRow(i,tempVal);
        }
        return row;
    }

}
