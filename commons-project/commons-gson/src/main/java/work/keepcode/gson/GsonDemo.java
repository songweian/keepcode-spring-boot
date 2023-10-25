package work.keepcode.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class GsonDemo {
    public static void main(String[] args) {
    }

    @Data
    public static class Animal {
        protected final String name;
    }

    @Getter
    @Setter
    public static class Dog extends Animal {
        private String breed;

        public Dog(String name, String breed) {
            super(name);
            this.breed = breed;
        }
    }

    @Getter
    @Setter
    public static class Cat extends Animal {
        private String color;

        public Cat(String name, String color) {
            super(name);
            this.color = color;
        }
    }

    public static class AnimalTypeAdapter extends TypeAdapter<Animal> {
        private static final String FIELD_TYPE = "type";
        private static final String TYPE_DOG = "dog";
        private static final String TYPE_CAT = "cat";

        @Override
        public void write(JsonWriter out, Animal value) throws IOException {
            out.beginObject();
            out.name(FIELD_TYPE).value(getTypeString(value));
            out.name("name").value(value.getName());
            if (value instanceof Dog) {
                out.name("breed").value(((Dog) value).getBreed());
            } else if (value instanceof Cat) {
                out.name("color").value(((Cat) value).getColor());
            }
            out.endObject();
        }

        @Override
        public Animal read(JsonReader in) throws IOException {
            in.beginObject();
            String type = null;
            String name = null;
            String breed = null;
            String color = null;
            while (in.hasNext()) {
                String fieldName = in.nextName();
                if (FIELD_TYPE.equals(fieldName)) {
                    type = in.nextString();
                } else if ("name".equals(fieldName)) {
                    name = in.nextString();
                } else if ("breed".equals(fieldName)) {
                    breed = in.nextString();
                } else if ("color".equals(fieldName)) {
                    color = in.nextString();
                } else {
                    in.skipValue();
                }
            }
            in.endObject();
            if (TYPE_DOG.equals(type)) {
                return new Dog(name, breed);
            } else if (TYPE_CAT.equals(type)) {
                return new Cat(name, color);
            } else {
                throw new IllegalStateException("Unknown animal type: " + type);
            }
        }

        private String getTypeString(Animal animal) {
            if (animal instanceof Dog) {
                return TYPE_DOG;
            } else if (animal instanceof Cat) {
                return TYPE_CAT;
            } else {
                throw new IllegalArgumentException("Unknown animal type: " + animal.getClass());
            }
        }
    }
}

