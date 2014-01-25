package server.viral;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.net.*;
import java.io.*;

public class Viral_MainActivity extends Activity {

	public static final String HOST_NAME = "68.7.237.78";
	public static final int PORT_NUMBER = 2001;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btnConnect = (Button)findViewById(R.id.btnConnect);
		btnConnect.setOnClickListener(new OnClickListener() 
		{
			
			public void onClick(View v) 
			{
				// Try to connect to server and get string
				try {
					TextView txtName = (TextView)findViewById(R.id.txtName);
		        	txtName.setText(Viral_MainActivity.connectToServer(HOST_NAME, PORT_NUMBER));
		        	Viral_MainActivity.connectToServer(HOST_NAME, PORT_NUMBER);
		        	
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public static String connectToServer(String hostName, int portNumber) throws UnknownHostException, IOException {
		
		// Not sure why I need serverAddr instead of just using hostName as a parameter, experimenting to see if that gets better results
		InetAddress serverAddr = InetAddress.getByName(hostName);
    	Socket serverSocket = new Socket(serverAddr, portNumber);
		BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		try {
			return in.readLine();
		}
		finally {
			if (serverSocket != null) serverSocket.close();
			if (in != null) in.close();
		}
    }

}

