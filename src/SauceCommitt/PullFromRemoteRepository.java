package com.saucelabs.test.Utils;
import java.io.File; 
import java.io.IOException; 
 
import org.eclipse.jgit.api.Git; 
import org.eclipse.jgit.api.errors.GitAPIException; 
import org.eclipse.jgit.api.errors.InvalidRemoteException; 
import org.eclipse.jgit.api.errors.TransportException; 
 
 
 
/**
 * Simple snippet which shows how to clone a repository from a remote source 
 * 
 * @author dominik.stadler at gmx.at 
 */ 
public class PullFromRemoteRepository { 
 
    private static final String REMOTE_URL = "https://github.com/Purushoth88/Sauce-Java-Sample-Working.git"; 
 
    public static void main(String[] args) throws IOException, InvalidRemoteException, TransportException, GitAPIException { 
        // prepare a new folder for the cloned repository 
        File localPath = File.createTempFile("TestGitRepository", ""); 
        localPath.delete(); 
 
        // then clone 
        
        System.out.println("Cloning from " + REMOTE_URL + " to " + localPath); 
        try (Git result = Git.cloneRepository() 
                .setURI(REMOTE_URL) 
                .setDirectory(localPath) 
                .call()) { 
         // Note: the call() returns an opened repository already which needs to be closed to avoid file handle leaks! 
         System.out.println("Having repository: " + result.getRepository().getDirectory()); 
         try (Git git = new Git(result.getRepository())) { 
                git.pull() 
                .call(); 
         } 
 
         System.out.println("Pulled from remote repository to local repository at " + result.getRepository().getDirectory()); 
 
            // workaround for https://bugs.eclipse.org/bugs/show_bug.cgi?id=474093 
            result.getRepository().close(); 
        } 
    } 
}