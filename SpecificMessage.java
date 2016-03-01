package iristk.app.movies;

public class SpecificMessage {

	public static String genreSpecificMessage(String genre){
		String message="";
		switch(genre){
			case "comedy":
				message="good choice, comedies are my favourite"; //add witty message here :))  good choice, comedies are my favourite
				break;
			case "action":
				message="good choice, I love a good car chase scene!"; // g c,  I love a good car chase scene!
				break;
			case "horror":
				message="I see you don't scare easily"; // 
				break;
			case "fantasy":
				message="Ah, the one genre to rule them all.";
				break;
			case "adventure":
				message="yes, I feel like watching an adventure movie too";
				break;
			case "science fiction":
				message="yes, I could go for a science fiction movie";
				break;
			case "romance":
				message="yes, I could go for a romantic movie";
				break;
			case "thriller":
				message="good choice, I got the chills already";
				break;
			//case "animation":
			//case "crime":
			//case "documentary":
			//case "drama":
			//case "family":
			//case "western":
			default:
				message="good choice, I feel like watching that too";
				break;
		}
		
		return message;
	}

	
	public static String movieSeriesSpecificMessage(String choice){
		String message="";
		switch(choice){
			case "movie":
				message="I hope you've brought popcorn!"; //add witty message here :))  good choice, comedies are my favourite
				break;
			default:
				message="Get ready to binge watch!";
				break;
		}	
		return message;
	}
	
	
}
