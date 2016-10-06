package com.saucelabs.test.Utils;
import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.errors.UnsupportedCredentialItem;
import org.eclipse.jgit.transport.CredentialItem;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.URIish;



/**
 * Simple snippet which shows how to clone a repository from a remote source
 * via ssh protocol and username/password authentication.
 *
 * @author Soha
 */
public class CloneRemoteRepositoryWithAuthentication {
    private static final String REMOTE_URL = "https://github.com/Purushoth88/Test.git";

    public static void main(String[] args) throws IOException, InvalidRemoteException, TransportException, GitAPIException {
        // this is necessary when the remote host does not have a valid certificate, ideally we would install the certificate in the JVM
        // instead of this unsecure workaround!
        CredentialsProvider allowHosts = new CredentialsProvider() {

            @Override
            public boolean supports(CredentialItem... items) {
                for(CredentialItem item : items) {
                    if((item instanceof CredentialItem.YesNoType)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public boolean get(URIish uri, CredentialItem... items) throws UnsupportedCredentialItem {
                for(CredentialItem item : items) {
                    if(item instanceof CredentialItem.YesNoType) {
                        ((CredentialItem.YesNoType)item).setValue(true);
                        return true;
                    }
                }
                return false;
            }

            @Override
            public boolean isInteractive() {
                return false;
            }
        };

        // prepare a new folder for the cloned repository
        File localPath = File.createTempFile("TestGitRepository", "");
        localPath.delete();

        // then clone
        System.out.println("Cloning from " + REMOTE_URL + " to " + localPath);
        try (Git result = Git.cloneRepository()
                .setURI(REMOTE_URL)
                .setDirectory(localPath)
                .setCredentialsProvider(allowHosts)
                .call()) {
        	
        	//Git git = result.call();
            // add
            AddCommand ac = result.add();
            ac.addFilepattern("test.txt");
            try {
                ac.call();
            } catch (NoFilepatternException e) {
                e.printStackTrace();
            }
            // commit
            CommitCommand commit = result.commit();
          commit.setMessage("text.txt");
            try {
                commit.call();
            } catch (NoHeadException e) {
                e.printStackTrace();
            } catch (NoMessageException e) {
                e.printStackTrace();
            } catch (ConcurrentRefUpdateException e) {
                e.printStackTrace();
            } catch (WrongRepositoryStateException e) {
                e.printStackTrace();
            }
	        // Note: the call() returns an opened repository already which needs to be closed to avoid file handle leaks!
	        System.out.println("Having repository: " + result.getRepository().getDirectory());

            // workaround for https://bugs.eclipse.org/bugs/show_bug.cgi?id=474093
            result.getRepository().close();
        }
    }
}