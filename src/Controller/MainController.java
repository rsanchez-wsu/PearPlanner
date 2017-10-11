	public static void initialise() {
		try {
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, key64);
		try (CipherInputStream cipherInputStream = new CipherInputStream(
						new BufferedInputStream(new FileInputStream(plannerFile)), cipher);
				ObjectInputStream inputStream = new ObjectInputStream(cipherInputStream);) {
			ui.showStartup();
			// If a file is present:
			if (plannerFile.exists()) {
				SealedObject sealedObject = (SealedObject) inputStream.readObject();
				SPC = new StudyPlannerController((StudyPlanner) sealedObject.getObject(cipher));

				// Sample note
				if (SPC.getPlanner().getCurrentStudyProfile() != null && SPC.getPlanner()
						.getCurrentStudyProfile().getName().equals("First year Gryffindor")) {
					UIManager.reportSuccess(
							"Note: This is a pre-loaded sample StudyPlanner, as used by Harry "
							+ "Potter. To make your own StudyPlanner, restart the application "
							+ "and choose \"New File\".");
				}

			} else {
				// This should never happen unless a file changes permissions or existence in the
				// miliseconds
				// that it runs the above code after checks in StartupController
				UIManager.reportError("Failed to load file.");
				System.exit(1);
			}

		} catch (FileNotFoundException e) {
			UIManager.reportError("File does not exist");
			System.exit(1);
		} catch (ClassNotFoundException e) {
			UIManager.reportError("Invalid file");
			System.exit(1);
		} catch (BadPaddingException e) {
			UIManager.reportError("Invalid file");
			System.exit(1);
		} catch (IOException e) {
			UIManager.reportError("Invalid file");
			System.exit(1);
		} catch (IllegalBlockSizeException e) {
			UIManager.reportError("Invalid file");
			System.exit(1);
		} catch (Exception e) {
			UIManager.reportError(e.getMessage());
			System.exit(1);
		}
		} catch(InvalidKeyException e) 
		{
			UIManager.reportError("Invalid Key, Cannot decode the given file");
            System.exit(1);
        }
		catch(NoSuchAlgorithmException e) 
		{
        	UIManager.reportError("Cannot decode the given file");
            System.exit(1);
        }
		catch(NoSuchPaddingException e) 
		{
        	UIManager.reportError("Invalid file, No Such Padding");
            System.exit(1);
        }
	}
