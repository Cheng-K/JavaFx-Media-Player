<a name="readme-top"></a>

<div align="center">
  <h1 align="center">Media Player</h1>
  <p align="center">
    A basic desktop media player
    <br/>
    <br />
    <a href="#usage">View Demo</a>
    ·
    <a href="https://github.com/Cheng-K/JavaFx-Media-Player/issues">Report Bug</a>
    ·
    <a href="https://github.com/Cheng-K/JavaFx-Media-Player/issues">Request Feature</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li>
   <a href="#usage">Usage</a>
   <ul>
      <li><a href="#loading-and-playing-media">Loading and playing media</a></li>
      <li><a href="#switching-media-from-playlist">Switching media from playlist</a></li>
      <li><a href="#basic-controls-of-player">Basic controls of player</a></li>
      <li><a href="#deleting-playlist">Deleting playlist</a></li>
   </ul>
   </li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->

## About The Project

![Product Name Screen Shot][product-screenshot]
Media Player is a desktop application that allows users to play videos. It supports basic functionalities such as playing, looping, adding media into playlist, and dragging the time slider will adjust the media playing.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

- [![Java][java-shield]][java-url]
- [![JavaFX][javafx-shield]][javafx-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->

## Getting Started

It is recommended to set up this project with [IntelliJ IDEA](https://www.jetbrains.com/idea/) and the following steps assumes IntelliJ IDEA is used.

### Prerequisites

- JDK version >= 16 _(recommended: 16)_
- JavaFX SDK >= 16 _(recommended: 16)_

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/Cheng-K/JavaFx-Media-Player.git
   ```
2. Follow this [guide](https://openjfx.io/openjfx-docs/#JavaFX-and-IntelliJ) to set up JavaFX in IntelliJ IDEA
3. Mark _'images' directory_ as resources in IntelliJ IDEA
   1. Navigate to 'Project Structure' > 'Modules'
   2. Select 'images' folder
   3. Select 'Resources' among the list of 'Mark As' options given
4. Modify VM options
   1. Assuming you have followed step 2. you should have configured the VM options in the Run Configuration
   2. Replace the VM options from step 2. with
      ```
      --module-path "\path\to\javafx-sdk-16\lib" --add-modules javafx.controls,javafx.fxml,javafx.media
      ```
5. Run the project

You have successfully set up the project 🎉

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->

## Usage

### Loading and playing media

![Demo for loading and playing media](./demo/demo-loading-playing.gif)
_[Video source](https://www.youtube.com/watch?v=m4-HM_sCvtQ)_

### Switching media from playlist

![Demo for switching media](./demo/demo-switching-media.gif)
_[Video source](https://www.youtube.com/watch?v=l9AzO1FMgM8)_

### Basic controls of player

![Demo for basic controls of player](./demo/demo-basic-controls.gif)

### Deleting playlist

![Demo for deleting playlist](./demo/demo-remove-playlist.gif)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- LICENSE -->

## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->

## Contact

Cheng Kei Ong - chengkei.ong@outlook.com - [![Linkedin][linkedin-shield]][linkedin-url]

Project Link: [https://github.com/Cheng-K/JavaFx-Media-Player](https://github.com/Cheng-K/JavaFx-Media-Player)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ACKNOWLEDGMENTS -->

## Acknowledgments

- [Fireship](https://www.youtube.com/@Fireship)
- [Best README Template](https://github.com/othneildrew/Best-README-Template)
- [Badges4-README.md-Profile](https://github.com/alexandresanlim/Badges4-README.md-Profile)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[license-url]: https://github.com/Cheng-K/JavaFx-Media-Player/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white
[linkedin-url]: https://linkedin.com/in/chengkei-ong
[product-screenshot]: demo/product-screenshot.png
[java-shield]: https://img.shields.io/badge/Java-E50914?style=for-the-badge
[java-url]: https://www.oracle.com/java/technologies/
[javafx-shield]: https://img.shields.io/badge/JavaFX-196e91?style=for-the-badge
[javafx-url]: https://openjfx.io/
