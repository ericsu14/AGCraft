import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import com.joojet.biblefetcher.constants.BibleID;
import com.joojet.biblefetcher.constants.BookID;
import com.joojet.biblefetcher.fetcher.BibleFetcher;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			BibleFetcher.getVerses(BibleID.KJV, BookID.GAL, 1);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
