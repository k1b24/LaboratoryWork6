package kib.lab6.server.Commands;

import kib.lab6.common.util.ErrorMessage;
import kib.lab6.common.util.Request;
import kib.lab6.common.util.SuccessMessage;
import kib.lab6.server.Config;
import kib.lab6.server.abstractions.AbstractCommand;

public class RemoveByID extends AbstractCommand {

    public RemoveByID() {
        super("remove_by_id", "Удалить человека из коллекции по"
                + " его ID, принимает на вход [ID]", false);
    }

    @Override
    public Object execute(Request request) {
        int id = Integer.parseInt(request.getStringArgumentToSend());
        if (id <= Config.getCollectionManager().getLength() && id > 0) {
            Config.getCollectionManager().removeHumanById(id);
            return new SuccessMessage("Человек с ID " + id + " успешно удален");
        } else {
            return new ErrorMessage("Человек с таким ID не найден");
        }
    }
}
