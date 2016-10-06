package com.saucelabs.test.Utils;
import java.io.File; 
 
import org.eclipse.jgit.storage.file.FileRepository; 
import org.junit.Before; 
import org.junit.Rule; 
import org.junit.rules.TemporaryFolder; 
 
public abstract class AbstractGitServiceImpl { 
 
    @Rule 
    public TemporaryFolder tempFolder = new TemporaryFolder(); 
 
    protected File remoteDirectory; 
    protected File localDirectory; 
    protected FileRepository remoteRepository; 
    protected FileRepository localRepository; 
 
    protected GitServiceImpl service; 
 
    @Before 
    public void setup() throws Exception { 
        remoteDirectory = tempFolder.newFolder("remote"); 
        localDirectory = tempFolder.newFolder("local"); 
        remoteRepository = RepositoryFixture.createRepository(remoteDirectory); 
        service = new GitServiceImpl("42"); 
        service.setLocalWorkspace(localDirectory.getAbsolutePath()); 
        service.setRemoteLocation(remoteDirectory.toURI().toURL().toExternalForm().replace("%20", " ")); 
        service.setWatchBranch("master"); 
    } 
}