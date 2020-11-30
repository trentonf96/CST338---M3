import java.util.*;

public class Assign3
{
   public static void main(String[] args)
   {
      Scanner input = new Scanner(System.in);

      // Test 3
      // Create deck out of 2 standard size decks and print the cards
      int count = 0;
      Deck deck1 = new Deck(2);
      while (true)
      {
         Card temp = deck1.dealCard();
         if (temp.getErrorFlag() == true)
         {
            break;
         } else
         {
            System.out.printf("%s / ", temp.toString());
            count++;
            if (count == 8)
            {
               System.out.println();
               count = 0;
            }
         }
      }

      // Re-initialize the deck, shuffle, and the print the cards
      System.out.println();
      deck1.init(2);
      deck1.shuffle();
      while (true)
      {
         Card temp = deck1.dealCard();
         if (temp.getErrorFlag() == true)
         {
            break;
         } else
         {
            System.out.printf("%s / ", temp.toString());
            count++;
            if (count == 8)
            {
               System.out.println();
               count = 0;
            }
         }
      }

      // Create single deck and print the cards
      System.out.println();
      Deck deck2 = new Deck();
      while (true)
      {
         Card temp = deck2.dealCard();
         if (temp.getErrorFlag() == true)
         {
            break;
         } else
         {
            System.out.printf("%s / ", temp.toString());
            count++;
            if (count == 4)
            {
               System.out.println();
               count = 0;
            }
         }
      }

      // Re-initialize the deck, shuffle, and the print the cards
      System.out.println();
      deck2.init(1);
      deck2.shuffle();
      while (true)
      {
         Card temp = deck2.dealCard();
         if (temp.getErrorFlag() == true)
         {
            break;
         } else
         {
            System.out.printf("%s / ", temp.toString());
            count++;
            if (count == 4)
            {
               System.out.println();
               count = 0;
            }
         }
      }

      // Test 4
      System.out.println();
      System.out.printf("%s", "How many hands? (1-10 please): ");
      int players = input.nextInt();
      System.out.printf("%s\n", "Here are the hands from the unshuffled deck: ");
      Hand[] hands = new Hand[players];
      Deck deck3 = new Deck();
      for (int i = 0; i < players; i++)
      {
         hands[i] = new Hand();
      }
      
      // Distribute all the cards to the players
      while (deck3.getTopCard() > 0)
      {
         for (int i = 0; i < players; i++)
         {
            if (deck3.getTopCard() > 0)
            {
               hands[i].takeCard(deck3.dealCard());
            } else
            {
               break;
            }
         }
      }

      // Display the hands
      for (int i = 0; i < players; i++)
      {
         System.out.printf("%s\n\n", hands[i].toString());
      }
      
      // Shuffle the deck, then distribute all the cards to the players
      for (int i = 0; i < players; i++)
      {
         hands[i].resetHand();
         hands[i] = new Hand();
      }
      deck3.init(1);
      deck3.shuffle();
      System.out.printf("%s\n", "Here are the hands from the SHUFFLED deck: ");
      while (deck3.getTopCard() > 0)
      {
         for (int i = 0; i < players; i++)
         {
            if (deck3.getTopCard() > 0)
            {
               hands[i].takeCard(deck3.dealCard());
            } else
            {
               break;
            }
         }
      }

      // Display the hands
      for (int i = 0; i < players; i++)
      {
         System.out.printf("%s\n\n", hands[i].toString());
      }

   }
}

class Card
{
   // Enumeration
   public static enum Suit
   {
      clubs, spades, hearts, diamonds
   };

   // Variables
   private char value;
   private Suit suit;
   private boolean errorFlag;

   // Full constructor
   Card(char value, Suit suit)
   {
      this.value = value;
      this.suit = suit;

      if (isValid(value, suit))
      {
         this.errorFlag = false;
      } else
      {
         this.errorFlag = true;
      }
   }

   // No argument constructor
   Card()
   {
      this.value = 'A';
      this.suit = Suit.spades;
   }

   // Display the card data
   public String toString()
   {
      if (errorFlag || this == null)
      {
         return "** illegal **";
      } else
      {
         return value + " of " + suit;
      }
   }

   // Mutator
   public boolean set(char value, Suit suit)
   {
      this.value = value;
      this.suit = suit;

      if (isValid(value, suit))
      {
         errorFlag = false;
         return true;
      } else
      {
         errorFlag = true;
         return false;
      }
   }

   // Accessors
   public char getValue()
   {
      return value;
   }

   public Suit getSuit()
   {
      return suit;
   }

   public boolean getErrorFlag()
   {
      return errorFlag;
   }

   // Mutators
   public void setValue(char value)
   {
      this.value = value;
   }

   public void setSuit(Suit suit)
   {
      this.suit = suit;
   }

   public void setFlag(boolean flag)
   {
      this.errorFlag = flag;
   }

   // Returns true if the members are identical
   public boolean equals(Card card)
   {
      if (this.getValue() == card.getValue() && this.getSuit() == card.getSuit())
      {
         return true;
      } else
      {
         return false;
      }
   }

   // checks for the legality of the parameters
   private boolean isValid(char value, Suit suit)
   {
      // Check validity of value
      if (value == 'A' || value == '2' || value == '3' || value == '4' || value == '5' || value == '6' || value == '7'
            || value == '8' || value == '9' || value == 'T' || value == 'J' || value == 'Q' || value == 'K')
      {
         return true;
      } else
      {
         return false;
      }
   }
}

class Hand
{
   // Variables and Arrays
   public static final int MAX_CARDS = 50;
   private Card[] myCards = new Card[MAX_CARDS];
   private int numCards;

   // Constructor
   Hand()
   {
      for (int i = 0; i < MAX_CARDS; i++)
      {
         this.myCards[i] = new Card();
      }
      numCards = 0;
   }

   // Removes all the cards from the hand
   public void resetHand()
   {
      myCards = new Card[MAX_CARDS];
   }

   // Adds card to myCard array
   public boolean takeCard(Card card)
   {
      if (numCards <= MAX_CARDS)
      {
         this.myCards[numCards] = new Card(card.getValue(), card.getSuit());
         numCards++;
         return true;
      } else
      {
         return false;
      }
   }

   // returns and removes the top card of the array
   public Card playCard()
   {
      Card topCard = myCards[numCards - 1];
      myCards[numCards - 1] = null;
      numCards--;
      return topCard;
   }

   // Display the hand
   public String toString()
   {
      String strCards = "Hand = ( ";
      for (int i = 0; i < numCards; i++)
      {
         strCards += myCards[i].toString() + ", ";
      }
      return strCards.substring(0, strCards.length() - 2) + " )";
   }

   // numCard Accessor
   public int getnumCards()
   {
      return numCards;
   }

   // Accessor for individual cards
   public Card inspectCard(int k)
   {
      if (k < 0 || k > numCards)
      {
         Card badCard = new Card();
         badCard.setFlag(true);
         return badCard;
      } else
      {
         return myCards[k];
      }
   }

}

class Deck
{
   // Variables and arrays
   Random rand = new Random();
   public static final int MAX_CARDS = 52 * 6;
   private static Card[] masterPack = new Card[52];
   private Card[] cards;
   private int topCard;
   private static boolean masterCreated = false;

   // Constructors
   Deck(int numPacks)
   {
      allocateMasterPack();
      init(numPacks);
   }

   Deck()
   {
      this(1);
   }

   // Re-populates card[]
   public void init(int numPacks)
   {
      this.cards = new Card[52 * numPacks];
      for (int i = 0; i < numPacks; i++)
      {
         for (int j = 0; j < masterPack.length; j++)
         {
            cards[j + (52*i)] = masterPack[j];
         }
      }
      this.topCard = 52 * numPacks;
   }

   // Mixes up the cards
   public void shuffle()
   {
      for (int i = 0; i < cards.length; i++)
      {
         int num = rand.nextInt(cards.length);
         Card temp = cards[i];
         cards[i] = cards[num];
         cards[num] = temp;
      }
   }

   // Returns and removes the top card
   public Card dealCard()
   {
      if (topCard - 1 < 0)
      {
         Card badCard = new Card();
         badCard.setFlag(true);
         return badCard;
      } else
      {
         Card top = cards[topCard - 1];
         cards[topCard - 1] = null;
         topCard--;
         return top;
      }
   }

   // Accessor for topCard
   public int getTopCard()
   {
      return this.topCard;
   }

   // Accessor for individual cards
   public Card inspectCard(int k)
   {
      if (k < 0 || k > topCard - 1)
      {
         Card badCard = new Card();
         badCard.setFlag(true);
         return badCard;
      } else
      {
         return cards[k];
      }
   }

   // Static Deck object used for copying to another object. Only executes once.
   private static void allocateMasterPack()
   {
      if (!masterCreated)
      {
         String values = "A23456789TJQK";
         int i = 0;
         for (int v = 0; v < values.length(); v++)
         {
            for (Card.Suit s : Card.Suit.values())
            {
               masterPack[i] = new Card(values.charAt(v), s);
               i++;
            }
         }
         masterCreated = true;
      }
   }
}
