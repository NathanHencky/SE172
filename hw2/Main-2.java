import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws TwitterException {
        // write your code here

        ConfigurationBuilder cf = new ConfigurationBuilder();

        cf.setDebugEnabled(true)

                .setOAuthConsumerKey("")
                .setOAuthConsumerSecret("")
                .setOAuthAccessToken("")
                .setOAuthAccessTokenSecret("");

        TwitterFactory tf = new TwitterFactory(cf.build());

        twitter4j.Twitter twitter = tf.getInstance();


        List<Status> status = twitter.getHomeTimeline();


//        for(Status st: status){
//
//            System.out.println(st.getUser().getName() + "---->" + st.getText());
//        }
        System.out.println("Here are the tweets:");
        System.out.println();
        for (int i = 0; i < status.size(); i++) {

            Status st = status.get(i);


            System.out.println(st.getUser().getName() + "----> " + st.getText());

        }

        System.out.println("Post Tweet");
        System.out.println();


        Status s = twitter.updateStatus("Life is a struggle!!");
        System.out.println(s.getText());

        System.out.println("Search tweets");
        System.out.println();

        Query query = new Query("WWE");
        QueryResult result = twitter.search(query);
//        for (Status st : result.getTweets()) {
//            System.out.println("@" + st.getUser().getScreenName() + ":" + st.getText());
//        }
        System.out.println(result.getTweets().stream()
                .map(item -> item.getText())
                .collect(Collectors.toList()));

    }

}
