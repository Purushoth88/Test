package com.saucelabs.test.Utils;
import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

/**
 * Simple snippet which shows how to create a new repository
 * 
 * @author Soha
 */
public class JGitHelper {

    public static Repository openJGitCookbookRepository() throws IOException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repository = builder
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build();
        return repository;
    }

    public static Repository createNewRepository() throws IOException {
        // prepare a new folder
        Repository repository = FileRepositoryBuilder.create( new File("/tmp/new_repo/.git"));
        repository.create();

        return repository;
    }
}