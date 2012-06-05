# Java client for [Cube](http://square.github.com/cube)

Java client for sending events to [Cube](http://square.github.com/cube).

## Limitations

* The `data` portion of events is currently limited to a single level (`Map<String, String>`). The Cube event specification allows for nested trees which still need to be added.
* The only `CubeClient` implementation currently included is UDP.

## Example

At the beginning of your class:

```java
// import statements
import com.mzsanford.cube.CubeClient;
import com.mzsanford.cube.UDPCubeClient;
import com.mzsanford.cube.Event;
```

Once during application start-up:

```java
// Both parameters are optional, defaulting to localhost:1180
CubeClient client = new UDPCubeClient("cube.example.com", 1180);
```

Once for each event you want to send:

```java
// Get some information you want to save. For example, request
// performance information
Map<String, String> myData = new HashMap<String, String>();
myData.put("path", "/requested/path");
myData.put("duration", 222);

Event event = new Event("request");
event.setData(myData);

// Send via UDP
client.send(event);
```