package servermgr;

/**
 * used to check how the server is operating
 * @see StatusDecorator
 *  in the futre will be outfitted with more than jusat return a string
 */
public class Operations extends StatusDecorator {
    public Operations(Status status) {
        super(status);
    }
    @Override
    public String getCurrentServerStatus(){
        return super.getCurrentServerStatus() + ", and is operating normally";
    }

}
