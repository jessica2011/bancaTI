package hackatrix.json;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

public class JSon {

    /**
     * @param titulo de cada columna
     * @param list lista de filas de consulta
     * @return StringBuilder (prueba01.json)
     */
    public static String forQry(String[] titulo, List<Object[]> list) {

        JsonArrayBuilder jab = Json.createArrayBuilder();

        list.stream().forEach((fil) -> {
            JsonObjectBuilder col = Json.createObjectBuilder();

            for (int i = 0; i < titulo.length; i++) {
                addJsonObject(col, titulo[i], fil[i]);
            }

            jab.add(col);
        });

        JsonArray ja = jab.build();
        return ja.toString();
    }

    /**
     * @param list lista de filas de consulta
     * @return StringBuilder (prueba07.json)
     */
    public static String forQry(List<Object[]> list) {

        JsonArrayBuilder fils = Json.createArrayBuilder();

        list.stream().forEach((Object[] fil) -> {
            JsonArrayBuilder cols = Json.createArrayBuilder();

            for (Object col : fil) {
                addJsonObject(cols, col);
            }

            fils.add(cols);
        });

        JsonArray ja = fils.build();
        return ja.toString();
    }

    public static String jqGrid(List<Object[]> list, // debe venir ID en primera columna
            Integer numpag, Integer[] pags_fils) {

        JsonObjectBuilder job = Json.createObjectBuilder();

        // datos iniciales
        job.add("page", numpag); // numero de pagina a mostrar
        job.add("total", pags_fils[0]); // total de paginas en la consulta
        job.add("records", pags_fils[1]); // total de filas en la consulta

        // filas
        JsonArrayBuilder rows = Json.createArrayBuilder();
        list.stream().forEach((Object[] fil) -> {

            // ID de fila, se agrega a objeto data
            JsonObjectBuilder data = Json.createObjectBuilder();
            addJsonObject(data, "id", fil[0]);

            // columnas de fila, sin considerar la primera (ID)
            JsonArrayBuilder cell = Json.createArrayBuilder();
            for (int i = 1; i < fil.length; i++) {
                addJsonObject(cell, fil[i]);
            }

            // agregando columnas despues del ID en objeto data
            data.add("cell", cell);

            // adicionando ID y columnas por fila
            rows.add(data);
        });

        job.add("rows", rows);
        return job.build().toString();
    }

    /**
     * @param list lista de id y text para el combo
     * @return StringBuilder
     */
    public static String forCbo(List<Object[]> list) {

        JsonArrayBuilder jab = Json.createArrayBuilder();

        list.stream().forEach((fil) -> {
            JsonObjectBuilder col = Json.createObjectBuilder();

            addJsonObject(col, "id", fil[0]);
            addJsonObject(col, "text", fil[1]);

            jab.add(col);
        });

        JsonArray ja = jab.build();
        return ja.toString();
    }

    /**
     * @param titu titulos de fila
     * @param data valores de fila
     * @return StringBuilder
     */
    public static String forUpd(String[] titu, Object[] data) {
        JsonArrayBuilder jab = Json.createArrayBuilder();

        JsonObjectBuilder col = Json.createObjectBuilder();
        for (int i = 0; i < titu.length; i++) {
            addJsonObject(col, titu[i], data[i]);
        }
        jab.add(col);

        JsonArray ja = jab.build();
        return ja.toString();
    }

    public static String forMsg(String msg) {
        JsonArrayBuilder jab = Json.createArrayBuilder();

        if ((msg != null) && (!msg.isEmpty())) {
            JsonObjectBuilder col = Json.createObjectBuilder();
            col.add("msg", msg);
            jab.add(col);
        }

        JsonArray ja = jab.build();
        return ja.toString();
    }

    public static String forMsg(List<String> list) {
        JsonArrayBuilder jab = Json.createArrayBuilder();

        list.stream().forEach((msg) -> {

            if ((msg != null) && (!msg.isEmpty())) {

                JsonObjectBuilder col = Json.createObjectBuilder();
                col.add("msg", msg);
                jab.add(col);
            }
        });

        JsonArray ja = jab.build();
        return ja.toString();
    }

    public static String empty() {
        return "[{}]";
    }

    /**
     * apoyo
     *
     * @param model recibe JsonObject
     * @return como cadena en un StringBuilder
     *
     */
    private static StringBuilder toSb(JsonObject model) {

        StringWriter sWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(sWriter)) {
            jsonWriter.writeObject(model);
        }

        StringBuilder sb = new StringBuilder(sWriter.toString());
        return sb;
    }

    private static void addJsonObject(JsonObjectBuilder job, String titulo, Object value) {

        if (value instanceof BigDecimal) {
            job.add(titulo, new BigDecimal(value.toString()));

        } else if (value instanceof BigInteger) {
            job.add(titulo, new BigInteger(value.toString()));

        } else if (value instanceof Boolean) {
            job.add(titulo, Boolean.valueOf(value.toString()));

        } else if (value instanceof Double) {
            job.add(titulo, Double.valueOf(value.toString()));

        } else if (value instanceof Integer) {
            job.add(titulo, Integer.valueOf(value.toString()));

        } else if (value instanceof Long) {
            job.add(titulo, Long.valueOf(value.toString()));

        } else {
            job.add(titulo, value.toString());
        }
    }

    private static void addJsonObject(JsonArrayBuilder job, Object value) {

        if (value instanceof BigDecimal) {
            job.add(new BigDecimal(value.toString()));

        } else if (value instanceof BigInteger) {
            job.add(new BigInteger(value.toString()));

        } else if (value instanceof Boolean) {
            job.add(Boolean.valueOf(value.toString()));

        } else if (value instanceof Double) {
            job.add(Double.valueOf(value.toString()));

        } else if (value instanceof Integer) {
            job.add(Integer.valueOf(value.toString()));

        } else if (value instanceof Long) {
            job.add(Long.valueOf(value.toString()));

        } else {
            job.add(value.toString());
        }
    }
}
