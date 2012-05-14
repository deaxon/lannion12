class AddProjectsPdf < ActiveRecord::Migration
  def up
    add_column :projects, :pdf, :string
  end

  def down
  end
end
