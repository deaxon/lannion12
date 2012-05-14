class AddNameUniquenessIndex < ActiveRecord::Migration
  def self.up
    add_index :users, :name, :unique => true
  end

end