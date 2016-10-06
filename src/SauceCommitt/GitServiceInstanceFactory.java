package com.saucelabs.test.Utils;
import java.util.Map; 
 
import org.openengsb.core.api.Connector; 
import org.openengsb.core.common.AbstractConnectorInstanceFactory; 
 
public class GitServiceInstanceFactory extends AbstractConnectorInstanceFactory<GitServiceImpl> { 
 
    @Override 
    public Connector createNewInstance(String id) { 
        return new GitServiceImpl(id); 
    } 
 
    @Override 
    public void doApplyAttributes(GitServiceImpl instance, Map<String, String> attributes) { 
        if (attributes.containsKey("repository")) { 
            instance.setRemoteLocation(attributes.get("repository")); 
        } 
        if (attributes.containsKey("workspace")) { 
            instance.setLocalWorkspace(attributes.get("workspace")); 
        } 
        if (attributes.containsKey("branch")) { 
            instance.setWatchBranch(attributes.get("branch")); 
        } 
        if (attributes.containsKey("submodulesHack")) { 
            instance.setSubmodulesHack(attributes.get("submodulesHack")); 
        } 
    } 
 
}