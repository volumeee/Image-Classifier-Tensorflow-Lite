# Image Classifier TensorFlow Lite

Image Classifier TensorFlow Lite is a mobile application that allows users to classify images using a pre-trained TensorFlow Lite model. Users can take a picture or select an image from their gallery, and the app will provide classification results.

## Features

- [x] Take a picture using the device camera
- [x] Select an image from the gallery
- [x] Classify the image using TensorFlow Lite model
- [x] Display top classification results

## Demo
https://github.com/user-attachments/assets/fe8cca43-e264-4405-aeb6-90375f88ead9

## Installation

Follow the steps below to install and run the application on your local environment.

### Prerequisites

- Android Studio
- An Android device or emulator

### Steps

1. **Clone this repository:**

   ```bash
   git clone https://github.com/volumee/Image-Classifier-TensorFlow-Lite.git
   cd Image-Classifier-TensorFlow-Lite
   ```
2. **Open the project in Android Studio:**
   ```bash
   Open Android Studio and select Open an existing Android Studio project. Navigate to the cloned repository directory and select it.
   ```
4. **Build the project:**
   ```bash
   Once the project is loaded, click on Build in the menu and select Make Project.
   ```
6. **Run the application:**
   ```bash
   Connect your Android device or start an emulator, then click on Run in the menu and select Run 'app'.
   ```

## Usage
### Home Screen
The home screen provides two options:
1. Take a picture - Open the camera to take a picture.
2. Select from gallery - Open the gallery to choose an image.
   
### Classification
After taking a picture or selecting an image from the gallery, the application will display the image and the classification results, including the top 1, top 2, and top 3 predictions.

### Project Structure
.
├── app
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── example
│   │   │   │           └── ml_with_tensorflowlite
│   │   │   │               ├── MainActivity.java
│   │   │   │               ├── ImageClassifier.java
│   │   │   │               ├── Utils.java
│   │   │   ├── res
│   │   │   │   ├── layout
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   ├── drawable
│   │   │   ├── AndroidManifest.xml
├── assets
│   ├── model.tflite
│   ├── labels.txt
└── etc..

### source
- https://universe.roboflow.com/national-university-of-singapore-odcpk/fruit-classification-pvbax
- https://github.com/IJ-Apps/Image-Classification-App-with-Custom-TensorFlow-Model

### Kontak
Jika Anda memiliki pertanyaan atau saran, silakan hubungi saya di mr.volumee@gmail.com

