class User < ActiveRecord::Base
  attr_accessible :nom, :email, :password, :password_confirmation
  email_regex = /\A[\w+\-.]+@[a-z\d\-.]+\.[a-z]+\z/i
  validates :nom,  :presence => true,
            :length   => { :maximum => 50 }
  validates :email, :presence   => true,
            :format     => { :with => email_regex },
            :uniqueness => { :case_sensitive => false }
  validates :password, :presence     => true,
            :confirmation => true,
            :length       => { :within => 6..40 }
  def has_password?(submitted_password)
    password == submitted_password
  end

  def self.authenticate(email, submitted_password)
    user = find_by_email(email)
    return nil  if user.nil?
    return user if user.has_password?(submitted_password)
  end
  def self.authenticate_with_password(id, cookie_password)
    user = find_by_id(id)
    (user && user.password == cookie_password) ? user : nil
  end
end
