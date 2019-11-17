package servermgr;

/**
 * the Status Decorator that makes the Decorator pattern possible, anything that extends this returns server info.
 * @see Status
 * @see Memory, for example
 */
public abstract class StatusDecorator implements Status {
    private Status status;
    public StatusDecorator(Status status){
        this.status=status;
    }
    @Override
    public String getCurrentServerStatus(){
        return status.getCurrentServerStatus();
    }


}
