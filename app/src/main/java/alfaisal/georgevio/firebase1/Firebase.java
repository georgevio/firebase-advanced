package alfaisal.georgevio.firebase1;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import androidx.annotation.NonNull;

public class Firebase {

	private FirebaseDatabase mDatabase;
	private DatabaseReference myRef;

	public Firebase() {
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		myRef = database.getReference("dB");
	}



	public void onDataChange(){
		// Read from the database
			myRef.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				// This method is called once with the initial value and again
				// whenever data at this location is updated.
				String value = null;
				for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
					//value = postSnapshot.getValue(String.class);
					Map<String, Object> map =
						  (Map<String, Object>) dataSnapshot.getValue();
					Log.d("George", "Value is: " + map);
				}
			}

			@Override
			public void onCancelled(DatabaseError error) {
				// Failed to read value
				Log.d("George", "Failed to read value.", error.toException());
			}
		});
	}

	public void writeNewUser(String userId,
	                          String name,String email) {
		User user = new User(name, email);
		myRef.child("users").child(userId).setValue(user);
	}

	public void writeWithSuccess(String userId, String user) {
		myRef.child("users").child(userId).setValue(user)
			  .addOnSuccessListener(new OnSuccessListener<Void>() {
				  @Override
				  public void onSuccess(Void aVoid) {
					  Log.d("George", "SUCCESS writing...");
				  }
			  }).addOnFailureListener(new OnFailureListener() {
			@Override
			public void onFailure(@NonNull Exception e) {
				Log.e("George", "Error: "+e);
			}
		});
	}
}

