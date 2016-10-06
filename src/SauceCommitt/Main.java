package com.saucelabs.test.Utils;
import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.api.Status;

public class Main {

     private static final String REMOTE_URL = "git://github.com/Purushoth88/Sauce-Java-Sample-Working.git";

        public static void main(String[] args) throws IOException, InvalidRemoteException, TransportException, GitAPIException {
            // prepare a new folder for the cloned repository
            File localPath = File.createTempFile("TestGitRepository", "");
            localPath.delete();

            // then clone
            System.out.println("Cloning from " + REMOTE_URL + " to " + localPath);
		    File gitWorkDir = new File("git://github.com/Purushoth88/Sauce-Java-Sample-Working.git");
			Git git = Git.init().setDirectory(new File(gitWorkDir, "test.txt")).setBare(true).call(); 

            // now open the created repository
  /*          Git git = Git.open(localPath); */
            Repository repository = git.getRepository();
        	final String repoPath = repository.getWorkTree().getAbsolutePath(); 

            System.out.println("Having repository: " + repository.getDirectory());

            Status status = new Git(repository).status().call();
            System.out.println("Added: " + status.getAdded());
            System.out.println("Changed: " + status.getChanged());
            System.out.println("Conflicting: " + status.getConflicting());
            System.out.println("ConflictingStageState: " + status.getConflictingStageState());
            System.out.println("IgnoredNotInIndex: " + status.getIgnoredNotInIndex());
            System.out.println("Missing: " + status.getMissing());
            System.out.println("Modified: " + status.getModified());
            System.out.println("Removed: " + status.getRemoved());
            System.out.println("Untracked: " + status.getUntracked());
            System.out.println("UntrackedFolders: " + status.getUntrackedFolders());

            repository.close();
        }
}