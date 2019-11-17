package servermgr;

/**
 * used to check the extensions a server is using.
 * @see StatusDecorator
 * in the futre will be outfitted with more than jusat return a string
 */
public class Extensions extends StatusDecorator {
    public Extensions(Status status) {
        super(status);
    }
    @Override
    public String getCurrentServerStatus(){
        return  super.getCurrentServerStatus() + ", and is using these extensions - [Hypervisor, Kubernetes, RAID-6]";
    }
}
