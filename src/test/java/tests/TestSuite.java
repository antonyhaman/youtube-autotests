package tests;

import audioUtils.AudioComparison;
import com.codeborne.selenide.WebDriverRunner;
import core.WebdriverService;
import fileUtils.FileUtils;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.pages.YoutubeGenericVideoPage;
import pageObject.pages.YoutubeTrendingPage;
import pageObject.utils.PageNavigation;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kotvertolet on 6/24/2017.
 */
public class TestSuite {

    private final static int NUMBER_OF_VIDEOS = 10;
    private final static int DURATION_MINUTES = 1;
    private final static int DURATION_SECONDS = 0;
    private List<String> filenamesWithAdblock;
    private List<String> filenamesWithoutAdblock;
    private final FileUtils fileUtils = new FileUtils();

    @DataProvider(name = "fileNames")
    public Object[][] createTestData() {
        filenamesWithAdblock = fileUtils.prepareFilenames(NUMBER_OF_VIDEOS, true);
        filenamesWithoutAdblock = fileUtils.prepareFilenames(NUMBER_OF_VIDEOS, false);

        return new Object[][]{
                {filenamesWithAdblock, true},
                {filenamesWithoutAdblock, false},
        };
    }

    @BeforeGroups(groups = {"recordGroup"})
    public void prepareAudioDir() {
        fileUtils.initAudioDir();
    }

    @AfterGroups({"recordGroup"})
    public void compareAudio() {
        new AudioComparison().checkSoundFileSimilarity(filenamesWithAdblock, filenamesWithoutAdblock);
    }

    @Test(dataProvider = "fileNames", groups = {"recordGroup"})
    public void test(List<String> fileNames, boolean adblockOn) {
        WebdriverService.initDriver(adblockOn);
        YoutubeTrendingPage page = PageNavigation.getPage(YoutubeTrendingPage.class);
        List<String> videosLinksList = page.getTopTrendingVideosFeedPanel().getTopTrendingVideos()
                .getVideoLinks(NUMBER_OF_VIDEOS);
        PageNavigation.getPage(YoutubeGenericVideoPage.class)
                .navigateToVideoAndRecordSoundClip(videosLinksList, fileNames, DURATION_MINUTES, DURATION_SECONDS);
        WebDriverRunner.getWebDriver().quit();

        ArrayList<File> fileList = new ArrayList<>(Arrays.asList((fileUtils.getFilesInOutputDir())));
        List<String> actualFilenamesInOutputDir = fileList.stream().map(File::getName).collect(Collectors.toList());
        fileNames.forEach(expectedFilename -> Assert.assertTrue(actualFilenamesInOutputDir.contains(expectedFilename)));
    }
}
