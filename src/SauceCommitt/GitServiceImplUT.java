package com.saucelabs.test.Utils;
import static org.hamcrest.Matchers.is; 
import static org.hamcrest.Matchers.not; 
import static org.hamcrest.Matchers.notNullValue; 
import static org.junit.Assert.assertThat; 
 
import java.io.File; 
import java.io.FileWriter; 
import java.io.IOException; 
 
import org.eclipse.jgit.lib.AnyObjectId; 
import org.eclipse.jgit.lib.Constants; 
import org.eclipse.jgit.lib.ObjectId; 
import org.eclipse.jgit.revwalk.RevCommit; 
import org.eclipse.jgit.revwalk.RevWalk; 
import org.eclipse.jgit.treewalk.TreeWalk; 
import org.junit.Test; 
import org.openengsb.domain.scm.CommitRef; 
 
/**
 * This class contains unit-tests which may require a special system setup and 
 * should not be run automatically therefore. 
 */ 
public class GitServiceImplUT extends AbstractGitServiceImpl { 
 
    @Test 
    public void pollWithEmptyWorkspace_shouldCloneRemoteRepository() throws IOException { 
        service.setRemoteLocation("git://github.com/Mercynary/myTestRepo.git"); 
        service.update(); 
        localRepository = service.getRepository(); 
        AnyObjectId id = localRepository.resolve(Constants.HEAD); 
        assertThat(id, notNullValue()); 
        assertThat(id.name(), is("2f610959a14c8f26549bee563ad4da8c65e1ee8b")); 
    } 
 
    @Test 
    public void addDirectory_shouldReturnObjectIdForChild() throws Exception { 
        localRepository = RepositoryFixture.createRepository(localDirectory); 
 
        String dir = "testDirectory"; 
        String file = "testFile"; 
        File parent = new File("", dir); 
        parent.mkdirs(); 
        File child = new File(parent, file); 
        FileWriter fw = new FileWriter(child); 
        fw.write(file + "\n"); 
        fw.close(); 
 
        CommitRef commitRef = service.add("testComment", parent); 
        assertThat(commitRef, is(notNullValue())); 
 
        AnyObjectId id = localRepository.resolve(Constants.HEAD); 
        RevCommit commit = new RevWalk(localRepository).parseCommit(id); 
        String search = dir + "/" + file; 
        TreeWalk treeWalk = TreeWalk.forPath(localRepository, search, new AnyObjectId[]{ commit.getTree() }); 
        assertThat(treeWalk, is(notNullValue())); 
 
        ObjectId objectId = treeWalk.getObjectId(treeWalk.getTreeCount() - 1); 
        assertThat(objectId, not(ObjectId.zeroId())); 
    } 
}