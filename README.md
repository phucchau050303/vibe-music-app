# Vibe Music Finder - A New Way To Search For Music</br>

This project utilise 3rd party API to achieve results

### TECHNOLOGIES:

- Architecture: Microservice</br>
- Programming Langugae: Java 21</br>
- Framework: Spring Boot</br>
- 3rd Party APIs: Gemini API, Deezer API, Spotify API (will be added later)</br>
- Deployment: AWS API Gateway, AWS Step Funcitons, AWS Lambda, AWS S3

### 🚩Pain points**

In the past, we usually search music like this way: 
```
Get on music services -> browse for playlists -> spend time to find the best playlist based on our mood
```
Although sounding simple, it sometimes can cost us over 10 or 15 minutes just to find the best one. <br>

_=> How about using AI to find it? our work is just telling it our mood, 
and AI will search for the best playlist accoring to our request._

### Solution
Vibe Musice Finder utilise Gemini to interpet natural langugae moods ("I'm feeling happy because I got a gift from my friend)
and map the result to a specific metadata for Spotify/Deezer to return suitable music

### Architecture
The project is built on a Serverless Microservice architecture as of these reasons:
- Each service is independent from each other, meaning that if 1 service fails, other services can operate as usual but taking the whole system down. Hence, the fault tolerance of the application is greatly enchanced.
- Microservice is very easy to integrate with AWS Lambda, which is an AWS Managed service, allowing us to focus on writing code, without minding about the complex system under it and saves a lot of money to maintain.
- As each service is indepentdent from others, it can be scaled bigger without affecting others.<br/>

![AWS Cloud Diagram for Vibe Music Finder.](https://peters-public-bucket.s3.ap-southeast-1.amazonaws.com/Screenshot+2026-04-18+231824.png)

The architecture flow can be seen on the image above

*_Client sends a POST request -> API Gateway receives the request and send it to Step Functions -> StepFunctions then start Intent Service ->
IntentProcessing call Gemini API to process natural languge -> Intent Service sends its output to MusicFetcher -> Music Fetcher calls Deezer API to fetch playlists ->
MusicFetcher sends the response back to client though API Gateway_*

### 


### Behind The Project
**Technology Selection Explaination** <br/>

*Architecture*<br/>

As we know, there are 2 popular types of architecture: Monolith and Microservices, each architecture has its own advantages and disadvantages.
For this project, I chose Microservices as my main architecture based on these reasons: <br>
- Each service is independent from each other, meaning that if 1 service fail, other service can operate as usual but taking the whole system down. Hence, the fault tolerance of the application is greatly enchanced.
- Microservice is very easy to integrate with AWS Lambda, which is an AWS Managed service, allowing us to focus on writing code, without minding about the complex system under it and saves a lot of money to maintain.
- As each service is indepentdent from others, I can scale one service bigger without affecting others.

*Programming Language and Framework*</br>

Java is an object oriented prorgamming language used by a lot of enterprise  due to its rich performance, strong community, cross-platform amd scalabily. Therefore, I 
used Java to built this project to enchance my Java Skill. Also, I think Spring framework is so great, as it offered lots of usable methods and functions. <br/>

Furthermore, Dependency Injection is
one of my favourite thing from this framework, allowing me to work with external dependencies pretty easily. Finally, I'm kinda impressed by the Annotation of the Spring framework. Of course, it 
has lots of great uses, but the best thing I got from it would be no need to write complex implementation in main method (lol).

*3rd Party API*

For this project, the core idea is using AI to find music. Training an AI/LLM model, however, requires knowledge, time and strong hardware (which is impossible with
my X1 Yoga laptop with 8th gen intel U CPU .-.). Therefore, using a third party API from AI providers would the the best choice. _So why Gemini?_ Easy, it offers free tier 
for its user, so I dont have to pay for the service, but still be alble to use and test the program.

About music service API, I use Deezer API first, as it doesnt require authorisation and premium subscription like Spotify. However, when the app is ready to live, I'add Spotify later.
