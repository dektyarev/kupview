package net.dektyarev.kupandaviewer.rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class OfferTypeAdapterFactory implements TypeAdapterFactory {

    private static final String ROOT_OBJ = "result";
    private static final String DATA_OBJ = "data";

    public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {

        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {

            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            public T read(JsonReader in) throws IOException {

                JsonElement jsonElement = elementAdapter.read(in);

                jsonElement = extractChildJsonObject(jsonElement, ROOT_OBJ);
                jsonElement = extractChildJsonObject(jsonElement, DATA_OBJ);

                return delegate.fromJsonTree(jsonElement);
            }
        }.nullSafe();
    }

    private JsonElement extractChildJsonObject(JsonElement jsonElement, String name) {
        JsonObject jsonObject = toJsonObject(jsonElement);
        if ((jsonObject != null) && jsonObject.has(name) && (jsonObject.get(name).isJsonObject() || jsonObject.get(name).isJsonArray())) {
            return jsonObject.get(name);
        }

        return jsonElement;
    }

    private JsonObject toJsonObject(JsonElement jsonElement) {
        if ((jsonElement != null) && jsonElement.isJsonObject()) {
            return jsonElement.getAsJsonObject();
        }
        return null;
    }

}
