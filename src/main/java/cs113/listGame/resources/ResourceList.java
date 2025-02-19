package cs113.listGame.resources;

import cs113.listGame.gamecore.GameObject;

public interface ResourceList {
    /// Adds a resource to the end of the resource list
    /// @param resource a valid game object resource
    void add(ResourceObject resource);

    /// Removes a single object
    /// @param resource a valid game object resource
    void remove(ResourceObject resource);

    /// Removes all resources after the specified resource
    /// @param resource a valid game object resource
    void truncate(ResourceObject resource);

    /// Each object in the list follows the previous item
    /// Initially called with the leader, so call each resource in the
    /// list with the previous element as the leader
    void follow(GameObject leader);
}
