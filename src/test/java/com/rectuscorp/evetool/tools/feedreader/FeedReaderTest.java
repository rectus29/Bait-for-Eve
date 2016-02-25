package com.rectuscorp.evetool.tools.feedreader;

import com.rectuscorp.evetool.tools.feedreader.impl.smf.SMFFeedParser;
import junit.framework.TestCase;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;

public class FeedReaderTest extends TestCase {

    @Test
    public void testRead() throws Exception {
        ArrayList<INode> iNodes = FeedReader.get().read(new URL("https://forum.federatis.fr/index.php?action=.xml"), new SMFFeedParser());
    }
}