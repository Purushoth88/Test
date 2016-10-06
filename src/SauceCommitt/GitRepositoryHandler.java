package com.saucelabs.test.Utils;
import org.eclipse.jgit.api.Git; 
import org.eclipse.jgit.api.errors.GitAPIException; 
 
public interface GitRepositoryHandler { 
 
   void updateGitRepository(Git git, GitRepositoryInfo repositoryInfo) throws GitAPIException; 
}