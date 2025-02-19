package cs113.listGame.resources;

import cs113.listGame.gamecore.GameObject;

public class ResourceArray implements ResourceList {
    ResourceObject[] resources = new ResourceObject[4];

    public ResourceArray() {}

    private boolean contains(ResourceObject resource) {
        for (int i = 0; i < resources.length; i++) {
            if (resources[i] == resource) return true;
        }
        return false;
    }

    @Override
    public void add(ResourceObject resource) {
        if (contains(resource)) { return; } // gotcha moment, don't add it twice!

        for (int i = 0; i < resources.length; i++) {
            if (resources[i] == null) {
                resources[i] = resource;
                return;
            }
        }
    }

    @Override
    public void remove(ResourceObject resource) {
        for (int i = 0; i < resources.length; i++) {
            if (resources[i] == resource) {
                resources[i] = null;
                return;
            }
        }
    }

    @Override
    public void truncate(ResourceObject resource) {
        boolean found = false;
        for (int i = 0; i < resources.length; i++) {
            if (resources[i] == resource) {
                found = true;
            }
            if (found) {
                resources[i] = null;
            }
        }
    }

    @Override
    public void follow(GameObject leader) {
        for (int i = 0; i < resources.length; i++) {
            if (resources[i] != null) {
                resources[i].moveTowards(leader.getEchoCenter());
                leader = resources[i];
            }
        }
    }
}
