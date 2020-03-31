package no.ntnu.idata2001.contacts.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AddressBookDB implements AddressBook, Serializable {
  // TODO: 31/03/2020 make serializable?
  private final EntityManagerFactory emf;
  private final EntityManager em;

  public AddressBookDB() {
    this.emf = Persistence.createEntityManagerFactory("contacts-server");
    this.em = this.emf.createEntityManager();
  }

  /**
   * Add a new contact to the address book.
   *
   * @param contact The contact to be added.
   */
  @Override
  public void addContact(ContactDetails contact) {
    em.getTransaction().begin();
    em.persist(contact);
    em.getTransaction().commit();
  }

  /**
   * Remove the contact with the given phonenumber from the address book.
   * The phone number should be one that is currently in use.
   *
   * @param phoneNumber The phone number to the contact to remove
   */
  @Override
  public void removeContact(String phoneNumber) {

    String jpql = "DELETE FROM ContactDetails t WHERE (t.phone = :phoneNum)";
    Query query = this.em.createQuery(jpql);
    query.setParameter("phoneNum", phoneNumber);
    em.getTransaction().begin();
    query.executeUpdate();
    em.getTransaction().commit();

  }

  /**
   * Returns all the contacts as a collection.
   *
   * @return all the contacts as a collection.
   */
  @Override
  public Collection<ContactDetails> getAllContacts() {
    String jpql = "SELECT c FROM ContactDetails c";
    Query query = this.em.createQuery(jpql);
    return query.getResultList();
  }

  @Override
  public Iterator<ContactDetails> iterator() {
    return getAllContacts().iterator();
  }

  /**
   * Closes entity managers and factories.
   */
  public void close(){
    this.em.close();
    this.emf.close();
  }
}
