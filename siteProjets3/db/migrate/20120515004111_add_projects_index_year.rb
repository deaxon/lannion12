class AddProjectsIndexYear < ActiveRecord::Migration
  def self.up
    add_column :projects, :annee_id, :integer
  end

  def self.down
    remove_column :projects, :annee_id
  end
end
