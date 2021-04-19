package alfaisal.georgevio.firebase1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

	private DatabaseReference usersdB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// creating an instance of class
		Firebase firebaseRef = new Firebase();

		firebaseRef.writeWithSuccess("Hello, World!", "iser");
		firebaseRef.writeWithSuccess("123", "George-2");

		firebaseRef.writeNewUser("34", "George", "george1@gmail.com");
		firebaseRef.writeNewUser("34", "George", "george2@gmail.com");


		// Other database instance
		FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
		DatabaseReference myRef2 =
			  mDatabase.getReference("other-database");
		myRef2.setValue("Hello to other database");
	}
}