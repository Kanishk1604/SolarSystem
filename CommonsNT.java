/*Function to create and return typeof "PointSound"*/

private static PointSound pointSound(String filename) {
		URL url = null;
		//String filename = "sounds/magic_bells.wav";
		try {
			url = new URL("file", "localhost", filename);
		} catch (Exception e) {
			System.out.println("Can't open " + filename);
		}
		
		MediaContainer pointMedia = new MediaContainer(url);
		pointMedia.setCacheEnable(true);
		
		
		PointSound ps = new PointSound();                    // create and position a point sound
		ps.setCapability(PointSound.ALLOW_MUTE_WRITE);
		ps.setCapability(PointSound.ALLOW_MUTE_READ);
		ps.setSoundData(pointMedia);
		ps.setEnable(true);
		ps.setLoop(-1);
		ps.setSchedulingBounds(CommonsNT.hundredBS);
		return ps;
	}

/*Function that take SimpleUniverse object and enable audio for it*/

private void enableAudio(SimpleUniverse simple_U) {

		 mixer = null;		                         // create a null mixer as a joalmixer
		Viewer viewer = simple_U.getViewer();
		viewer.getView().setBackClipDistance(20.0f);         // make object(s) disappear beyond 20f 

		if (mixer == null && viewer.getView().getUserHeadToVworldEnable()) {			                                                 
			mixer = new JOALMixer(viewer.getPhysicalEnvironment());
			if (!mixer.initialize()) {                       // add mixer as audio device if successful
				System.out.println("Open AL failed to init");
				viewer.getPhysicalEnvironment().setAudioDevice(null);
			}
		}
	}