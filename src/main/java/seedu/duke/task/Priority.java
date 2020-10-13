package seedu.duke.task;

import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public enum Priority implements Jsonable {
    HIGH {
        @Override
        public String toJson() {
            final StringWriter writeable = new StringWriter();
            try {
                this.toJson(writeable);
            } catch (IOException e) {
                System.out.println("[Error] Cannot convert this project to JSON");
                e.printStackTrace();
            }
            return writeable.toString();
        }

        @Override
        public void toJson(Writer writer) throws IOException {
            writer.write("HIGH");
            writer.flush();
        }

        public String toString() {
            return "High priority";
        }
    },

    MEDIUM {
        @Override
        public String toJson() {
            final StringWriter writeable = new StringWriter();
            try {
                this.toJson(writeable);
            } catch (IOException e) {
                System.out.println("[Error] Cannot convert this project to JSON");
                e.printStackTrace();
            }
            return writeable.toString();
        }

        @Override
        public void toJson(Writer writer) throws IOException {
            writer.write("MEDIUM");
            writer.flush();
        }

        public String toString() {
            return "Medium priority";
        }
    },

    LOW {
        @Override
        public String toJson() {
            final StringWriter writeable = new StringWriter();
            try {
                this.toJson(writeable);
            } catch (IOException e) {
                System.out.println("[Error] Cannot convert this project to JSON");
                e.printStackTrace();
            }
            return writeable.toString();
        }

        @Override
        public void toJson(Writer writer) throws IOException {
            writer.write("LOW");
            writer.flush();
        }
        
        public String toString() {
            return "Low priority";
        }
    }
}
