package com.saucelabs.test.Utils;
import java.io.File; 
import java.io.FileWriter; 
import java.io.IOException; 
 
import org.eclipse.jgit.api.AddCommand; 
import org.eclipse.jgit.api.CommitCommand; 
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.AbortedByHookException;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException; 
import org.eclipse.jgit.api.errors.NoHeadException; 
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.UnmergedPathsException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException; 
import org.eclipse.jgit.errors.UnmergedPathException; 
import org.eclipse.jgit.storage.file.FileRepository; 
import org.eclipse.jgit.storage.file.FileRepositoryBuilder; 
 
public final class RepositoryFixture { 
 
    private RepositoryFixture() { 
    } 
 
    public static FileRepository createRepository(File directory) throws Exception { 
        FileRepository repository = create(directory); 
        Git git = new Git(repository); 
        addFile(git, "testfile"); 
        commit(git, "initial commit"); 
        return repository; 
    } 
 
    public static void addFile(Git git, String filename) throws IOException, Exception { 
        FileWriter writer = new FileWriter(new File(git.getRepository().getWorkTree(), filename)); 
        writer.write(filename + "\n"); 
        writer.close(); 
        AddCommand add = git.add(); 
        add.addFilepattern(filename).call(); 
    } 
 
    public static void commit(Git git, String message) throws UnmergedPathException, 
        Exception, AbortedByHookException, GitAPIException { 
        CommitCommand commit = git.commit(); 
        commit.setMessage(message).call(); 
    } 
 
    private static FileRepository create(File directory) throws IOException { 
        FileRepositoryBuilder builder = new FileRepositoryBuilder(); 
        FileRepository repository = (FileRepository) builder.setWorkTree(directory).build(); 
        repository.create(); 
        return repository; 
    } 
}