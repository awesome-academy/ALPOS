package alpos.uploader.local;

import alpos.uploader.ImageUpload;
import org.springframework.core.io.Resource;

public class LocalStorageImageUpload implements ImageUpload {

    private String storedPath;
    private Resource resource;

    @Override
    public String getStoredPath() {
        return storedPath;
    }

    @Override
    public void setStoredPath(String storedPath) {
        this.storedPath = storedPath;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String getUrl() {
        if (resource == null) {
            return "";
        }
        return "images/" + storedPath;
    }

}
