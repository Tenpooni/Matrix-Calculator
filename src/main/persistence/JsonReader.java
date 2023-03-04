package persistence;

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

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Column read() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parseColumn(jsonArray);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }




    private Column parseColumn(JSONArray columnArray) {

        int columnSize = columnArray.length();
        Column col = new Column(columnSize);

        for (int i = 0; i < columnSize; i++) {
            JSONArray buildRow = columnArray.getJSONArray(i);
            Row rowToAdd = parseRows(col, buildRow);
            col.setColumn(i, rowToAdd);
        }
        return col;
    }

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
