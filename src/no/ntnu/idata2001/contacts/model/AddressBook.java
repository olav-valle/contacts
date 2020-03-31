package no.ntnu.idata2001.contacts.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * Represents an Address book containing contacts with contact details.
 * Based on the example in the book "Objects first with Java" by David J. Barnes
 * and Michael Kölling.
 *
 * <p>Each contact is stored in a TreeMap using the phone number as the key.
 *
 * @author David J. Barnes and Michael Kölling and Arne Styve
 * @version 2020.03.16
 */
public interface AddressBook extends Serializable, Iterable<ContactDetails> {
  /**
   * Add a new contact to the address book.
   *
   * @param contact The contact to be added.
   */
  void addContact(ContactDetails contact);

  /**
   * Remove the contact with the given phonenumber from the address book.
   * The phone number should be one that is currently in use.
   *
   * @param phoneNumber The phone number to the contact to remove
   */
  void removeContact(String phoneNumber);

  /**
   * Returns all the contacts as a collection.
   *
   * @return all the contacts as a collection.
   */
  Collection<ContactDetails> getAllContacts();

  void close();

  @Override
  Iterator<ContactDetails> iterator();
}
