package com.saucelabs.test.Utils;
import static org.hamcrest.Matchers.is; 
import static org.hamcrest.Matchers.notNullValue; 
import static org.hamcrest.Matchers.nullValue; 
import static org.junit.Assert.assertThat; 
 
import java.io.File; 
 
import org.eclipse.jgit.revwalk.RevWalk; 
import org.eclipse.jgit.storage.file.FileRepository; 
import org.junit.Rule; 
import org.junit.Test; 
import org.junit.rules.TemporaryFolder; 
 
public class RepositoryFixtureTest { 
 
    @Rule 
    public TemporaryFolder temporaryFolder = new TemporaryFolder(); 
 
    @Test 
    public void createSampleDirectory_shoulHaveHiddenDirectoryDotGit() throws Exception { 
        File folder = temporaryFolder.newFolder("sample"); 
        assertThat(folder.isDirectory(), is(true)); 
        FileRepository repository = RepositoryFixture.createRepository(folder); 
        assertThat(new File(folder, ".git").isDirectory(), is(true)); 
        repository.close(); 
    } 
 
    @Test 
    public void createSampleDirectory_shouldHaveOneCommit() throws Exception { 
        File folder = temporaryFolder.newFolder("sample"); 
        FileRepository repository = RepositoryFixture.createRepository(folder); 
        assertThat(repository.getBranch(), is("master")); 
        RevWalk walk = new RevWalk(repository); 
        walk.markStart(walk.parseCommit(repository.resolve("refs/heads/master"))); 
        assertThat(walk.next(), notNullValue()); 
        assertThat(walk.next(), nullValue()); 
        repository.close(); 
    } 
}