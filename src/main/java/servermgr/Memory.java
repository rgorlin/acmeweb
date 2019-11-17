package servermgr;

/**
 * used to check the memory of the server
 * @see StatusDecorator
 * in the futre will be outfitted with more than jusat return a string
 */
public class Memory extends StatusDecorator {
    public Memory(Status status) {
        super(status);
    }
    @Override
    public String getCurrentServerStatus(){
        return super.getCurrentServerStatus() + ", and it's memory is running low";

    }

}
