/**
 * Created by Anton Skudarnov on 24.02.2017.
 */

/**
 * That class implements special kind of HashEntry. It is needed for marking cell of removed elements in Hash Table.
 *
 */
public class DeletedHashEntry extends HashEntry {
    private static DeletedHashEntry entry = null;
    DeletedHashEntry(){
        super(-1,-1);
    }

    public static DeletedHashEntry uniquieDeletedEntry(){
        if (entry == null){
            entry = new DeletedHashEntry();
        }
        return entry;
    }
}
