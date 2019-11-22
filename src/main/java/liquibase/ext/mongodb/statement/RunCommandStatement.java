package liquibase.ext.mongodb.statement;

import com.mongodb.client.MongoDatabase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.bson.Document;

@Getter
@EqualsAndHashCode(callSuper = true)
public class RunCommandStatement extends AbstractMongoDocumentStatement<Document> {

    public static final String COMMAND = "runCommand";

    protected Document command;

    public RunCommandStatement(String command) {
        this(BsonUtils.orEmptyDocument(command));
    }

    public RunCommandStatement(final Document command) {
        this.command = command;
    }

    @Override
    public String toJs() {
        return
                "db."
                        + COMMAND
                        + "("
                        + command.toJson()
                        + ");";
    }

    @Override
    public Document run(final MongoDatabase db) {
        return db.runCommand(command);
    }

    @Override
    public String toString() {
        return toJs();
    }


}